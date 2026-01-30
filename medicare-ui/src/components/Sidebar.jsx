// import { Link } from "react-router-dom";
// import { LayoutDashboard, UserDoctor, Users } from "lucide-react";

// export default function Sidebar() {
//   return (
//     <div className="w-64 h-screen bg-blue-900 text-white fixed">
//       <div className="p-5 text-2xl font-bold border-b border-blue-700">
//         🏥 MediCare
//       </div>

//       <nav className="mt-6 flex flex-col gap-2">
//         <Link className="flex items-center gap-3 px-5 py-3 hover:bg-blue-700"
//           to="/dashboard">
//           <LayoutDashboard /> Dashboard
//         </Link>

//         <Link className="flex items-center gap-3 px-5 py-3 hover:bg-blue-700"
//           to="/doctors">
//           <UserDoctor /> Doctors
//         </Link>

//         <Link className="flex items-center gap-3 px-5 py-3 hover:bg-blue-700"
//           to="/patients">
//           <Users /> Patients
//         </Link>
//       </nav>
//     </div>
//   );
// }
import { NavLink } from "react-router-dom";
import { LayoutDashboard, User, Users } from "lucide-react";
import { motion } from "framer-motion";

export default function Sidebar() {
  return (
    <motion.div
      initial={{ x: -200 }}
      animate={{ x: 0 }}
      transition={{ duration: 0.6 }}
      className="fixed h-screen w-64 glass p-6"
    >
      <h1 className="text-xl font-bold mb-8 text-blue-700">
        MediCare
      </h1>

      <nav className="space-y-4">
        <NavLink to="/" className="flex items-center gap-3 p-3 rounded-xl hover:bg-white/40">
          <LayoutDashboard size={20} /> Dashboard
        </NavLink>

        <NavLink to="/doctors" className="flex items-center gap-3 p-3 rounded-xl hover:bg-white/40">
          <User size={20} /> Doctors
        </NavLink>

        <NavLink to="/patients" className="flex items-center gap-3 p-3 rounded-xl hover:bg-white/40">
          <Users size={20} /> Patients
        </NavLink>
      </nav>
    </motion.div>
  );
}
