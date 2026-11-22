import { getPipelinesRequest } from "./mock-requests";

import type { FlatMockServerConfig } from "mock-config-server";

const mockServerConfig: FlatMockServerConfig = [
  {
    port: 8080,
    baseUrl: "/api/v1",
  },
  {
    name: "rest",
    configs: [getPipelinesRequest],
  },
];

export default mockServerConfig;
