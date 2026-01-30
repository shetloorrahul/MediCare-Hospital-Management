import { Routes, Route } from "react-router-dom";
import LandingPage from "../pages/LandingPage";
import Dashboard from "../pages/Dashboard";
import DoctorsList from "../pages/Doctors/DoctorsList";
import PatientsList from "../pages/Patients/PatientsList";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/doctors" element={<DoctorsList />} />
      <Route path="/patients" element={<PatientsList />} />
    </Routes>
  );
}
