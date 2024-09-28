import React, { useState } from "react";
import {
  DesktopOutlined,
  FileOutlined,
  MenuFoldOutlined,
  MenuUnfoldOutlined,
  PieChartOutlined,
  TeamOutlined,
  UserOutlined,
} from "@ant-design/icons";
import type { MenuProps } from "antd";
import { Button, Layout, Menu, theme } from "antd";
import { Link } from "react-router-dom";
import ListUser from "../../pages/admin/user/ListUser";
import Dashboard from "../../pages/admin/Dashboard";

const { Header, Sider, Content } = Layout;

const Layouts: React.FC = () => {
  const [selectMenuItem, setSelectMenuItem] = useState<string>("/dashboard");

  const renderContent = () => {
    switch (selectMenuItem) {
      case "users":
        return <ListUser />;
      case "/":
        return <Dashboard />;
      default:
        return <div>deklkasdj</div>;
    }
  };

  const items: MenuProps["items"] = [
    {
      key: "1",
      icon: <PieChartOutlined />,
      label: <Link to={"/auth/login"}>Đăng nhập</Link>,
      title: "Đăng nhập",
    },
    {
      key: "2",
      icon: <DesktopOutlined />,
      label: "Option 2",
    },
    {
      key: "sub1",
      icon: <UserOutlined />,
      label: "User",
      children: [
        {
          key: "3",
          label: "Danh sách người dùng",
          onClick: () => {
            setSelectMenuItem("users");
          },
        },
        {
          key: "4",
          label: <Link to={"/auth/forgot-password"}>Quên mật khẩu</Link>,
        },
        {
          key: "5",
          label: <Link to={"/auth/forgot-password"}>Quên mật khẩu</Link>,
        },
      ],
    },
    {
      key: "sub2",
      icon: <TeamOutlined />,
      label: "Team",
      children: [
        {
          key: "6",
          label: "Team 1",
        },
        {
          key: "7",
          label: "Team 2",
        },
      ],
    },
    {
      key: "9",
      icon: <FileOutlined />,
    },
  ];

  const [collapsed, setCollapsed] = useState(true);
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  return (
    <Layout>
      <Sider trigger={null} collapsible collapsed={collapsed}>
        <div className="demo-logo-vertical" />
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={["3"]}
          defaultOpenKeys={["sub1"]}
          items={items}
        />
      </Sider>
      <Layout>
        <Header style={{ padding: 0, background: colorBgContainer }}>
          <Button
            type="text"
            icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
            onClick={() => setCollapsed(!collapsed)}
            style={{
              width: 64,
            }}
            className="w-[100px] h-[64px] text-[16px]  p-5"
          />
        </Header>
        <Content
          style={{
            margin: "24px 16px",
            padding: 24,
            minHeight: 280,
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
          }}
        >
          {renderContent()}
        </Content>
      </Layout>
    </Layout>
  );
};

export default Layouts;
