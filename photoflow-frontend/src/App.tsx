import { Container, Title } from "@mantine/core";
import { Outlet } from "@tanstack/react-router";

export const App = () => (
  <Container>
    <Title>PhotoFlow</Title>
    <Outlet />
  </Container>
);
