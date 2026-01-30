import { Link } from "react-router-dom";
import { motion } from "framer-motion";

export default function LandingPage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-white">

      {/* HERO */}
      <section className="flex flex-col items-center justify-center text-center px-6 py-24">
        <motion.h1
          initial={{ opacity: 0, y: -30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.6 }}
          className="text-4xl md:text-5xl font-extrabold text-blue-700 mb-4"
        >
          MediCare Hospital
        </motion.h1>

        <motion.p
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.2 }}
          className="text-gray-600 max-w-xl mb-8"
        >
          A modern healthcare management system providing seamless doctor,
          patient, and appointment services.
        </motion.p>

        <motion.div
          initial={{ opacity: 0, scale: 0.9 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ delay: 0.4 }}
          className="flex gap-4"
        >
          <Link
            to="/dashboard"
            className="bg-blue-600 text-white px-6 py-3 rounded-xl shadow hover:bg-blue-700 transition"
          >
            Go to Dashboard
          </Link>

          <Link
            to="/patients"
            className="border border-blue-600 text-blue-600 px-6 py-3 rounded-xl hover:bg-blue-50 transition"
          >
            View Patients
          </Link>
        </motion.div>
      </section>

      {/* ABOUT */}
      <section className="px-6 py-20 bg-white">
        <div className="max-w-5xl mx-auto grid md:grid-cols-2 gap-10 items-center">
          <motion.div
            initial={{ opacity: 0, x: -40 }}
            whileInView={{ opacity: 1, x: 0 }}
            transition={{ duration: 0.6 }}
          >
            <h2 className="text-3xl font-bold text-gray-800 mb-4">
              About MediCare
            </h2>
            <p className="text-gray-600 leading-relaxed">
              MediCare is a microservices-based hospital management platform
              built using Spring Boot, React, and cloud-ready architecture.
              It simplifies doctor management, patient records, and
              appointment scheduling with a modern UI.
            </p>
          </motion.div>

          <motion.div
            initial={{ opacity: 0, x: 40 }}
            whileInView={{ opacity: 1, x: 0 }}
            transition={{ duration: 0.6 }}
            className="bg-blue-100 rounded-2xl p-10 text-center shadow"
          >
            <h3 className="text-xl font-bold text-blue-700 mb-2">
              Trusted Healthcare
            </h3>
            <p className="text-gray-600">
              Secure • Scalable • Reliable
            </p>
          </motion.div>
        </div>
      </section>

      {/* SERVICES */}
      <section className="px-6 py-20 bg-gray-50">
        <div className="max-w-6xl mx-auto text-center">
          <h2 className="text-3xl font-bold mb-10 text-gray-800">
            Our Services
          </h2>

          <div className="grid md:grid-cols-3 gap-8">
            {[
              {
                title: "Doctor Management",
                desc: "Add, update, and manage doctors efficiently.",
              },
              {
                title: "Patient Records",
                desc: "Secure patient data with full CRUD operations.",
              },
              {
                title: "Appointments",
                desc: "Schedule and track appointments easily.",
              },
            ].map((service, i) => (
              <motion.div
                key={i}
                initial={{ opacity: 0, y: 30 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{ delay: i * 0.2 }}
                className="bg-white p-6 rounded-2xl shadow hover:shadow-lg transition"
              >
                <h3 className="text-xl font-semibold text-blue-600 mb-2">
                  {service.title}
                </h3>
                <p className="text-gray-600">{service.desc}</p>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA */}
      <section className="px-6 py-20 bg-blue-600 text-white text-center">
        <h2 className="text-3xl font-bold mb-4">
          Ready to Manage Your Hospital?
        </h2>
        <p className="mb-6">
          Access your dashboard and start managing doctors and patients.
        </p>
        <Link
          to="/dashboard"
          className="bg-white text-blue-600 px-8 py-3 rounded-xl font-semibold hover:bg-gray-100 transition"
        >
          Get Started
        </Link>
      </section>

    </div>
  );
}
