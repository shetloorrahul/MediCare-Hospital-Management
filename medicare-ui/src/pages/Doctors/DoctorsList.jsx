import { useEffect, useState } from "react";
import Sidebar from "../../components/Sidebar";
import api from "../../api/axios";
import DoctorForm from "./DoctorForm";
import { toast } from "react-toastify";

export default function DoctorsList() {
  const [doctors, setDoctors] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editDoctor, setEditDoctor] = useState(null);
  const [deleteId, setDeleteId] = useState(null);

  const loadDoctors = async () => {
    try {
      const res = await api.get("/doctor-api/getAll");
      setDoctors(res.data);
    } catch {
      toast.error("Failed to load doctors");
    }
  };

  useEffect(() => {
    loadDoctors();
  }, []);

  const confirmDelete = async () => {
    try {
      await api.delete(`/doctor-api/delete/${deleteId}`);
      toast.success("Doctor deleted successfully");
      setDeleteId(null);
      loadDoctors();
    } catch {
      toast.error("Delete failed");
    }
  };

  return (
    <div className="flex">
      <Sidebar />

      <div className="ml-64 p-8 w-full">
        <div className="flex justify-between mb-6">
          <h1 className="text-2xl font-bold">Doctors</h1>
          <button
            onClick={() => {
              setEditDoctor(null);
              setShowForm(true);
            }}
            className="btn-primary"
          >
            + Add Doctor
          </button>
        </div>

        <table className="w-full bg-white rounded-xl shadow">
          <thead className="bg-gray-100">
            <tr>
              <th>Name</th>
              <th>Specialization</th>
              <th>Experience</th>
              <th>Contact</th>
              <th>Email</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {doctors.length === 0 ? (
              <tr>
                <td colSpan={6} className="text-center py-4">
                  No doctors found.
                </td>
              </tr>
            ) : (
              doctors.map((d) => (
                <tr key={d.id} className="border-t text-center">
                  <td>{d.name}</td>
                  <td>{d.specialization}</td>
                  <td>{d.experience}</td>
                  <td>{d.contactno}</td>
                  <td>{d.email}</td>
                  <td className="space-x-2">
                    <button
                      onClick={() => {
                        setEditDoctor(d);
                        setShowForm(true);
                      }}
                      className="bg-yellow-400 px-3 py-1 rounded"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => setDeleteId(d.id)}
                      className="bg-red-500 text-white px-3 py-1 rounded"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>

        {/* ADD / EDIT MODAL */}
        {showForm && (
          <DoctorForm
            closeModal={() => setShowForm(false)}
            refresh={loadDoctors}
            editDoctor={editDoctor}
          />
        )}

        {/* DELETE CONFIRMATION */}
        {deleteId && (
          <div className="fixed inset-0 bg-black/40 flex items-center justify-center">
            <div className="bg-white p-6 rounded-xl shadow">
              <h2 className="text-lg font-bold mb-4">
                Confirm Delete Doctor?
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
