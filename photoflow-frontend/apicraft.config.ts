import { apicraft } from "@siberiacancode/apicraft";

export default apicraft([
  {
    input: "./openapi.yaml",
    output: "./src/_generated/api",
    instance: "axios",
    baseUrl: "/api/v1",
    nameBy: "path",
    groupBy: "tags",
    plugins: ["tanstack", "typescript", "zod"],
  },
]);
