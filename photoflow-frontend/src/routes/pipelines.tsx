import { createFileRoute } from "@tanstack/react-router";

import { PipelinesPage } from "@/pages/pipelines";

export const Route = createFileRoute("/pipelines")({
  component: PipelinesPage,
});
