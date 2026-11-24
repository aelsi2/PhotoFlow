import { Container, Title } from "@mantine/core";
import { Link, Outlet } from "@tanstack/react-router";

export const App = () => (
  <Container>
    <Title>
      <Link to="/" style={{ textDecoration: "none", color: "inherit" }}>
        PhotoFlow
      </Link>
    </Title>
    <Outlet />
  </Container>
);
