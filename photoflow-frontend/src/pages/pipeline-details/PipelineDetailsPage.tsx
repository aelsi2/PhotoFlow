import { Group, Text, Title } from "@mantine/core";
import { getRouteApi } from "@tanstack/react-router";

import { useGetPipelineByIdSuspenseQuery } from "@/_generated/api";
import { zGetPipelineByIdResponse } from "@/_generated/api/zod.gen";

const routeApi = getRouteApi("/pipelines/$pipelineId");

export const PipelineDetailsPage = () => {
  const params = routeApi.useParams();
  const pipelineQuery = useGetPipelineByIdSuspenseQuery({
    request: {
      path: {
        id: params.pipelineId,
      },
    },
    params: {
      select: (data) => zGetPipelineByIdResponse.parse(data.data),
    },
  });

  return (
    <Group>
      <Title order={2}>
        <Text c="dimmed" inherit span>
          #{pipelineQuery.data.id}
        </Text>{" "}
        {pipelineQuery.data.name}
      </Title>
    </Group>
  );
};
