import { Routes, Route } from "react-router-dom";
import LandingPage from "./pages/LandingPage";
import Dashboard from "./pages/Dashboard";
import DoctorsList from "./pages/doctors/DoctorsList";
import PatientsList from "./pages/patients/PatientsList";

function App() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/doctors" element={<DoctorsList />} />
      <Route path="/patients" element={<PatientsList />} />
    </Routes>
  );
}

export default App;

