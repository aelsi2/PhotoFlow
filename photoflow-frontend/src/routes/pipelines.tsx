import { Text } from "@mantine/core";
import { createFileRoute } from "@tanstack/react-router";

import { PipelinesPage } from "@/pages/pipelines";

export const Route = createFileRoute("/pipelines")({
  component: PipelinesPage,
  errorComponent: ({ error }) => <Text>{String(error)}</Text>,
  pendingComponent: () => <Text>Loading...</Text>,
});
