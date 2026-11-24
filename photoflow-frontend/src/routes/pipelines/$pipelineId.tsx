import { createFileRoute } from "@tanstack/react-router";
import { z } from "zod";

import { PipelineDetailsPage } from "@/pages/pipeline-details";

export const Route = createFileRoute("/pipelines/$pipelineId")({
  params: {
    parse: (rawParams) => ({
      pipelineId: z.coerce
        .number()
        .int()
        .nonnegative()
        .parse(rawParams.pipelineId),
    }),
    stringify: (params) => ({
      pipelineId: String(params.pipelineId),
    }),
  },
  component: PipelineDetailsPage,
});
