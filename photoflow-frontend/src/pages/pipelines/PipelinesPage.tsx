import { Table } from "@mantine/core";

import { useGetPipelinesSuspenseQuery } from "@/_generated/api";
import { zGetAllPipelinesResponse } from "@/_generated/api/zod.gen";

export const PipelinesPage = () => {
  const pipelinesQuery = useGetPipelinesSuspenseQuery({});

  const pipelinesQueryResult = zGetAllPipelinesResponse.parse(
    pipelinesQuery.data.data,
  );

  return (
    <Table>
      <Table.Thead>
        <Table.Tr>
          <Table.Th>Pipeline Id</Table.Th>
          <Table.Th>Pipeline Name</Table.Th>
        </Table.Tr>
      </Table.Thead>
      <Table.Tbody>
        {pipelinesQueryResult.map((pipeline) => (
          <Table.Tr key={pipeline.id}>
            <Table.Td>{pipeline.id}</Table.Td>
            <Table.Td>{pipeline.name}</Table.Td>
          </Table.Tr>
        ))}
      </Table.Tbody>
    </Table>
  );
};
