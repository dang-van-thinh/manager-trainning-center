import { useForm, SubmitHandler } from "react-hook-form";
import { login } from "../services/AuthService";
import { Navigate, useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import { Button } from "antd";
import { useState } from "react";

type Inputs = {
  email: string;
  password: string;
};

export default function Login() {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<Inputs>();

  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  
  const loginHandler: SubmitHandler<Inputs> = async (data) => {
    setLoading(true);
    const loged = await login(data);
    console.log("Login page console " + loged);
    if (loged) {
      navigate("/dashboard");
      toast.success("Đăng nhập thành công !");
    } else {
      // toast.error("Tài khoản hoặc mật khẩu không chính xác !");
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-zinc-100">
      <div className="shadow-lg w-full max-w-md p-8  font-semibold bg-white rounded-xl ">
        <h2 className="text-center uppercase font-bold mb-5">Login</h2>
        <form action="" onSubmit={handleSubmit(loginHandler)}>
          <div className="mb-4">
            <label htmlFor="email" className="block text-sm mb-2 font-bold">
              Email:
            </label>
            <input
              type="email"
              className="border p-3 h-11 w-full font-medium rounded-md focus:outline-none focus:ring"
              placeholder="Nhập email"
              {...register("email", { required: true })}
            />
            {errors.email && (
              <span className="text-red-600 font-bold text-xs">
                Email không được để trống !
              </span>
            )}
          </div>
          <div className="mb-4">
            <label htmlFor="email" className="block text-sm mb-2 font-bold">
              Mật khẩu:
            </label>
            <input
              type="password"
              className="border p-3 h-11 w-full font-medium rounded-md focus:outline-none focus:ring"
              placeholder="Nhập mật khẩu"
              {...register("password", { required: true })}
            />
            {errors.password && (
              <span className="text-red-600 font-bold text-xs">
                Mật khẩu không được để trống !
              </span>
            )}
          </div>
          <div className="mb-4">
            <Button
              className="w-full p-4"
              loading={loading}
              type="primary"
              htmlType="submit"
            >
              Đăng nhập
            </Button>
          </div>
          <div className="mb-4">
            <a href="" className="text-sm text-blue-700 hover:underline">
              Quên mật khẩu ?
            </a>
          </div>
          <div className="text-sm text-slate-600">
            Chào mừng bạn đến với ứng dụng quản lý trung tâm
          </div>
        </form>
      </div>
    </div>
  );
}
