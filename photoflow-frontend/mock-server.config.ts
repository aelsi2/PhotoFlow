import type { FlatMockServerConfig } from "mock-config-server";

const mockServerConfig: FlatMockServerConfig = [
  {
    port: 8080,
    baseUrl: "/api/v1/",
    database: {
      data: {
        pipelines: [{ id: 1, name: "Do Work" }],
        settings: {
          blocked: false,
        },
      },
    },
  },
];

export default mockServerConfig;
