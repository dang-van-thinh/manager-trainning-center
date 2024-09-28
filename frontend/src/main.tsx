import { StrictMode, useState } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import { Toast, ToastBar, Toaster } from "react-hot-toast";
import ListUser from "./pages/admin/user/ListUser";

import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Login from "./pages/auth/Login";
import Dashboard from "./pages/admin/Dashboard";
import Layouts from "./components/layout/Layout";
import ForgotPassword from "./pages/auth/ForgotPassword";

const router = createBrowserRouter([
  {
    path: "/auth/login",
    element: <Login />,
  },
  {
    path: "/auth/forgot-password",
    element: <ForgotPassword />,
  },
  {
    path: "/",
    element: <Layouts />,
    // children: [
    //   { path: "/dashboard", element: <Dashboard /> },
    //   { path: "/user/list", element: <ListUser /> },
    // ],
  },
]);

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <RouterProvider router={router} />
    <Toaster position="top-center" reverseOrder={true} />
  </StrictMode>
);
