import { useEffect, useState } from "react";
import Sidebar from "../../components/Sidebar";
import api from "../../api/axios";
import PatientForm from "./PatientForm";
import { toast } from "react-toastify";

export default function PatientsList() {
  const [patients, setPatients] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editPatient, setEditPatient] = useState(null);
  const [deleteId, setDeleteId] = useState(null);

  const loadPatients = async () => {
    try {
      const res = await api.get("/patient-api/getALl");
      setPatients(res.data);
    } catch {
      toast.error("Failed to load patients");
    }
  };

  useEffect(() => {
    loadPatients();
  }, []);

  const confirmDelete = async () => {
    try {
      await api.delete(`/patient-api/delete/${deleteId}`);
      toast.success("Patient deleted successfully");
      setDeleteId(null);
      loadPatients();
    } catch {
      toast.error("Delete failed");
    }
  };

  return (
    <div className="flex">
      <Sidebar />

      <div className="ml-64 p-8 w-full">
        <div className="flex justify-between mb-6">
          <h1 className="text-2xl font-bold">Patients</h1>
          <button
            onClick={() => {
              setEditPatient(null);
              setShowForm(true);
            }}
            className="btn-primary"
          >
            + Add Patient
          </button>
        </div>

        <table className="w-full bg-white rounded-xl shadow">
          <thead className="bg-gray-100">
            <tr>
              <th>Name</th>
              <th>Age</th>
              <th>Gender</th>
              <th>Doctor</th>
              <th>Problem</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {patients.map((p) => (
              <tr key={p.id} className="border-t text-center">
                <td>{p.name}</td>
                <td>{p.age}</td>
                <td>{p.gender}</td>
                <td>{p.doctorVo?.name}</td>
                <td>{p.problem}</td>
                <td className="space-x-2">
                  <button
                    onClick={() => {
                      setEditPatient(p);
                      setShowForm(true);
                    }}
                    className="bg-yellow-400 px-3 py-1 rounded"
                  >
                    Edit
                  </button>

                  <button
                    onClick={() => setDeleteId(p.id)}
                    className="bg-red-500 text-white px-3 py-1 rounded"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* ADD / EDIT MODAL */}
        {showForm && (
          <PatientForm
            closeModal={() => setShowForm(false)}
            refresh={loadPatients}
            editPatient={editPatient}
          />
        )}

        {/* DELETE CONFIRMATION */}
        {deleteId && (
          <div className="fixed inset-0 bg-black/40 flex items-center justify-center">
            <div className="bg-white p-6 rounded-xl shadow">
              <h2 className="text-lg font-bold mb-4">
                Confirm Delete Patient?
              </h2>
              <div className="flex justify-between">
                <button
                  onClick={confirmDelete}
                  className="bg-red-600 text-white px-4 py-2 rounded"
                >
                  Yes
                </button>
                <button
                  onClick={() => setDeleteId(null)}
                  className="btn-secondary"
                >
                  Cancel
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
