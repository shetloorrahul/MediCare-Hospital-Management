import { useEffect, useState } from "react";
import Sidebar from "../components/Sidebar";
import api from "../api/axios";
import { toast } from "react-toastify";
import {
  BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer
} from "recharts";


export default function Dashboard() {
  const [doctorCount, setDoctorCount] = useState(0);
  const [patientCount, setPatientCount] = useState(0);
  const [chartData, setChartData] = useState([]);

  useEffect(() => {
    loadCounts();
    loadChart();
  }, []);

  const loadCounts = async () => {
    try {
      const doctors = await api.get("/doctor-api/getAll");
      const patients = await api.get("/patient-api/getALl");

      setDoctorCount(doctors.data.length);
      setPatientCount(patients.data.length);
    } catch {
      toast.error("Failed to load dashboard counts");
    }
  };

  const loadChart = async () => {
    try {
      const patients = await api.get("/patient-api/getALl");

      const map = {};
      patients.data.forEach(p => {
        const docName = p.doctorVo?.name || "Unknown";
        map[docName] = (map[docName] || 0) + 1;
      });

      const formatted = Object.keys(map).map(key => ({
        doctor: key,
        patients: map[key]
      }));

      setChartData(formatted);
    } catch {
      toast.error("Failed to load chart data");
    }
  };

  return (
    <div className="flex">
      <Sidebar />

      <div className="ml-64 p-8 w-full">
        <h1 className="text-2xl font-bold mb-6">Dashboard</h1>

        {/* COUNTS */}
        <div className="grid grid-cols-2 gap-6 mb-8">
          <div className="bg-white p-6 rounded-xl shadow">
            <h2 className="text-gray-500">Total Doctors</h2>
            <p className="text-4xl font-bold">{doctorCount}</p>
          </div>

          <div className="bg-white p-6 rounded-xl shadow">
            <h2 className="text-gray-500">Total Patients</h2>
            <p className="text-4xl font-bold">{patientCount}</p>
          </div>
        </div>
         
        {/* CHART */}
        <div className="bg-white p-6 rounded-xl shadow">
  <h2 className="text-xl font-bold mb-4">
    Patients per Doctor
   </h2>
     <ResponsiveContainer width="100%" aspect={2}>
        <BarChart data={chartData}>
            <XAxis dataKey="doctor" />
            <YAxis allowDecimals={false} />
           <Tooltip />
          <Bar dataKey="patients" />
         </BarChart>
        </ResponsiveContainer>
</div>

      </div>
    </div>
  );
}

