import { Table, Text } from "@mantine/core";

import { useGetPipelinesQuery } from "@/_generated/api";
import { zGetAllPipelinesResponse } from "@/_generated/api/zod.gen";

export const PipelinesPage = () => {
  const pipelinesQuery = useGetPipelinesQuery({});

  if (pipelinesQuery.isLoading) {
    return <Text>Loading</Text>;
  }

  if (pipelinesQuery.isError || !pipelinesQuery.data) {
    return <Text>Error</Text>;
  }

  const pipelinesQueryResult = zGetAllPipelinesResponse.safeParse(
    pipelinesQuery.data.data,
  );
  if (!pipelinesQueryResult.success) {
    return <Text>Error</Text>;
  }

  return (
    <Table>
      <Table.Thead>
        <Table.Tr>
          <Table.Th>Pipeline Id</Table.Th>
          <Table.Th>Pipeline Name</Table.Th>
        </Table.Tr>
      </Table.Thead>
      <Table.Tbody>
        {pipelinesQueryResult.data.map((pipeline) => (
          <Table.Tr key={pipeline.id}>
            <Table.Td>{pipeline.id}</Table.Td>
            <Table.Td>{pipeline.name}</Table.Td>
          </Table.Tr>
        ))}
      </Table.Tbody>
    </Table>
  );
};
