import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

import { createBrowserRouter, RouterProvider } from "react-router-dom";

import Login from "./pages/Login";
import Dashboard from "./pages/admin/Dashboard";
import "./index.css";
import { Toast, ToastBar, Toaster } from "react-hot-toast";
import Layouts from "./components/layout/Layout";
const router = createBrowserRouter([
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/",
    element: <Layouts />,
    children: [{ path: "/dashboard", element: <Dashboard /> }],
  },
]);

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <RouterProvider router={router} />
    <Toaster position="top-center" reverseOrder={true} />
  </StrictMode>
);
