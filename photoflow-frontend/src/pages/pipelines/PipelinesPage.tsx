import { Badge, Button, Group, Table, TextInput, Title } from "@mantine/core";
import { useQueryClient } from "@tanstack/react-query";
import { useState } from "react";

import {
  getPipelinesQueryKey,
  useGetPipelinesSuspenseQuery,
  usePostPipelinesMutation,
  type CreatePipelineData,
} from "@/_generated/api";
import { zGetAllPipelinesResponse } from "@/_generated/api/zod.gen";

export const PipelinesPage = () => {
  const queryClient = useQueryClient();
  const pipelinesQuery = useGetPipelinesSuspenseQuery({
    params: {
      select: (data) => zGetAllPipelinesResponse.parse(data.data),
    },
  });
  const createPipelineMutation = usePostPipelinesMutation({
    params: {
      onSuccess: () => {
        setNewPipelineData({ name: "" });
        return queryClient.invalidateQueries({
          queryKey: [getPipelinesQueryKey],
        });
      },
    },
  });
  const [newPipelineData, setNewPipelineData] = useState<
    CreatePipelineData["body"]
  >({ name: "" });

  return (
    <>
      <Group justify="space-between">
        <Group gap="xs">
          <Title order={2}>Pipelines</Title>
          <Badge variant="light" size="lg">
            {pipelinesQuery.data.length}
          </Badge>
        </Group>
        <form
          onSubmit={(event) => {
            event.preventDefault();
            createPipelineMutation.mutate({
              body: newPipelineData,
            });
          }}
        >
          <Group>
            <TextInput
              value={newPipelineData.name}
              placeholder="New pipeline name"
              disabled={createPipelineMutation.isPending}
              onChange={(event) =>
                setNewPipelineData({
                  ...newPipelineData,
                  name: event.target.value,
                })
              }
            />
            <Button
              disabled={!newPipelineData.name.trim()}
              loading={createPipelineMutation.isPending}
              type="submit"
            >
              Create Pipeline
            </Button>
          </Group>
        </form>
      </Group>
      <Table>
        <Table.Thead>
          <Table.Tr>
            <Table.Th>Id</Table.Th>
            <Table.Th>Name</Table.Th>
          </Table.Tr>
        </Table.Thead>
        <Table.Tbody>
          {pipelinesQuery.data.map((pipeline) => (
            <Table.Tr key={pipeline.id}>
              <Table.Td>{pipeline.id}</Table.Td>
              <Table.Td>{pipeline.name}</Table.Td>
            </Table.Tr>
          ))}
        </Table.Tbody>
      </Table>
    </>
  );
};
