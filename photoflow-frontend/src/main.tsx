import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { createRouter, RouterProvider } from "@tanstack/react-router";
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

import { routeTree } from "@/_generated/routeTree.gen";

import "./index.css";
import '@mantine/core/styles.css';
import { createTheme, MantineProvider } from "@mantine/core";

const router = createRouter({ routeTree });
const queryClient = new QueryClient();
const theme = createTheme({});

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <MantineProvider theme={theme}>
      <QueryClientProvider client={queryClient}>
        <RouterProvider router={router} />
      </QueryClientProvider>
    </MantineProvider>
  </StrictMode>,
);
