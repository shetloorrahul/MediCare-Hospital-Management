import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import api from "../../api/axios";
import { toast } from "react-toastify";

export default function PatientForm({ closeModal, refresh, editPatient }) {
  const { register, handleSubmit, setValue, formState: { errors } } = useForm();
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    api.get("/doctor-api/getAll")
      .then(res => setDoctors(res.data))
      .catch(() => toast.error("Failed to load doctors"));
  }, []);

  useEffect(() => {
    if (editPatient) {
      Object.keys(editPatient).forEach(key => {
        if (key !== "doctorVo") setValue(key, editPatient[key]);
      });
      setValue("doctorId", editPatient.doctorVo?.id);
    }
  }, [editPatient]);

  const onSubmit = async (data) => {
    try {
      const payload = {
        ...data,
        doctorVo: { id: data.doctorId }
      };
      delete payload.doctorId;

      if (editPatient) {
        await api.put(`/patient-api/update/${editPatient.id}`, payload);
        toast.info("Patient updated successfully");
      } else {
        await api.post("/patient-api/register", payload);
        toast.success("Patient registered successfully");
      }

      refresh();
      closeModal();
    } catch {
      toast.error("Operation failed");
    }
  };

  return (
    <div className="fixed inset-0 bg-black/40 flex justify-center items-center">
      <div className="bg-white p-6 rounded-xl w-[420px] shadow-xl">
        <h2 className="text-xl font-bold mb-4">
          {editPatient ? "Edit Patient" : "Register Patient"}
        </h2>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">
          <input {...register("name", { required: true })} className="input" placeholder="Name" />
          <input {...register("age")} className="input" placeholder="Age" />

          <select {...register("gender")} className="input">
            <option value="">Select Gender</option>
            <option>Male</option>
            <option>Female</option>
          </select>

          <input {...register("contactno")} className="input" placeholder="Contact No" />
          <input {...register("address")} className="input" placeholder="Address" />
          <input {...register("problem")} className="input" placeholder="Problem" />

          <select {...register("doctorId", { required: true })} className="input">
            <option value="">Assign Doctor</option>
            {doctors.map(d => (
              <option key={d.id} value={d.id}>
                {d.name} – {d.specialization}
              </option>
            ))}
          </select>

          <div className="flex justify-between mt-4">
            <button className="btn-primary">Save</button>
            <button type="button" onClick={closeModal} className="btn-secondary">
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
