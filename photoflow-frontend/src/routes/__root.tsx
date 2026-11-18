import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import { createRootRoute } from "@tanstack/react-router";
import { TanStackRouterDevtools } from "@tanstack/react-router-devtools";

import { App } from "@/App";

export const Route = createRootRoute({
  component: () => (
    <>
      <App />
      <ReactQueryDevtools />
      <TanStackRouterDevtools />
    </>
  ),
});
