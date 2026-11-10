import type { RestRequestConfig } from "mock-config-server";

export const getPipelinesRequest: RestRequestConfig = {
  path: "/pipelines",
  method: "get",
  routes: [
    {
      data: [
        { id: 1, name: "Moon photos" },
        { id: 2, name: "Pick better blur" },
        { id: 3, name: "Do work" },
      ],
    },
  ],
};
