import { useEffect } from "react";
import { useForm } from "react-hook-form";
import api from "../../api/axios";
import { toast } from "react-toastify";

export default function DoctorForm({ closeModal, refresh, editDoctor }) {
  const {
    register,
    handleSubmit,
    setValue,
    formState: { errors },
  } = useForm();

  useEffect(() => {
    if (editDoctor) {
      Object.keys(editDoctor).forEach((key) => setValue(key, editDoctor[key]));
    }
  }, [editDoctor, setValue]);

  const onSubmit = async (data) => {
    try {
      if (editDoctor) {
        await api.put(`/doctor-api/update/${editDoctor.id}`, data);
        toast.info("Doctor updated successfully");
      } else {
        await api.post("/doctor-api/register", data);
        toast.success("Doctor added successfully");
      }
      refresh();
      closeModal();
    } catch (err) {
      console.error(err);
      toast.error("Operation failed");
    }
  };

  return (
    <div className="fixed inset-0 bg-black/40 flex justify-center items-center z-50">
      <div className="bg-white p-6 rounded-xl w-[420px] shadow-xl">
        <h2 className="text-xl font-bold mb-4 text-center">
          {editDoctor ? "Edit Doctor" : "Add Doctor"}
        </h2>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">
          <input
            {...register("name", { required: "Name is required" })}
            className="input"
            placeholder="Name"
          />
          {errors.name && <p className="text-red-500 text-sm">{errors.name.message}</p>}

          <input
            {...register("specialization", { required: "Specialization required" })}
            className="input"
            placeholder="Specialization"
          />
          {errors.specialization && (
            <p className="text-red-500 text-sm">{errors.specialization.message}</p>
          )}

          <input
            type="number"
            {...register("experience", { required: "Experience required" })}
            className="input"
            placeholder="Experience (years)"
          />
          {errors.experience && (
            <p className="text-red-500 text-sm">{errors.experience.message}</p>
          )}

          <input
            type="number"
            {...register("contactno", {
              required: "Contact number required",
              minLength: { value: 10, message: "Enter 10-digit number" },
              maxLength: { value: 10, message: "Enter 10-digit number" },
            })}
            className="input"
            placeholder="Contact Number"
          />
          {errors.contactno && (
            <p className="text-red-500 text-sm">{errors.contactno.message}</p>
          )}

          <input
            type="email"
            {...register("email", { required: "Email required" })}
            className="input"
            placeholder="Email"
          />
          {errors.email && <p className="text-red-500 text-sm">{errors.email.message}</p>}

          <div className="flex justify-between mt-4">
            <button type="submit" className="btn-primary">
              {editDoctor ? "Update" : "Save"}
            </button>
            <button type="button" onClick={closeModal} className="btn-secondary">
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
