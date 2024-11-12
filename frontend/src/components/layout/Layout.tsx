import Header from "./partials/Header";
import Footer from "./partials/Footer";
import { Outlet } from "react-router-dom";
function Layout() {
  return (
    <>
      <Header />
        <main>
        <Outlet />
        </main>
      <Footer />
    </>
  );
}
export default Layout;
