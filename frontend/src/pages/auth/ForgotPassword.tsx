import { ArrowLeftOutlined, SendOutlined } from "@ant-design/icons";
import { Button } from "antd";
import { useForm, SubmitHandler } from "react-hook-form";
import { Link } from "react-router-dom";

type Inputs = {
  email: string;
};

export default function ForgotPassword() {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<Inputs>();

  const submit = () => {
    alert("ádsa");
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-zinc-100">
      <div className="shadow-lg w-full max-w-md p-8  font-semibold bg-white rounded-xl ">
        <div className="mb-2">
          <Link
            to={"/auth/login"}
            className="hover:shadow-lg hover:border opacity-2 px-4 py-3 mx-2 rounded-md"
          >
            <ArrowLeftOutlined className="text-black font-bold" />
          </Link>
        </div>
        <h2 className="text-center uppercase font-bold mb-5">Quên mật khẩu</h2>
        <form action="" onSubmit={handleSubmit(submit)}>
          <div className="mb-4">
            <label htmlFor="email" className="block mb-2 text-bold">
              Email :
            </label>
            <input
              type="email"
              className="border p-2 w-full rounded-lg font-normal focus:outline-none focus:ring"
              placeholder="example@gmail.com"
              {...register("email", { required: true })}
            />
            {errors.email && (
              <span className="text-red-600 font-bold text-xs">
                Email không được để trống !
              </span>
            )}
          </div>
          <div className="mb-3 flex justify-end">
            <Button
              type="primary"
              htmlType="submit"
              className="px-5 text-lg"
              title="Gửi"
            >
              <SendOutlined className="p-3" />
            </Button>
          </div>
        </form>
      </div>
    </div>
  );
}
