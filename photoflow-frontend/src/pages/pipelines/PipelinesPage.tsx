import type { GetAllPipelinesResponse } from "@/_generated/api";
import { Table } from "@mantine/core";

export const PipelinesPage = () => {
  const pipelines: GetAllPipelinesResponse = [
    { id: 0, name: "pipeline 0" },
    { id: 1, name: "pipeline 1" },
    { id: 2, name: "pipeline 2" },
    { id: 3, name: "pipeline 3" },
    { id: 4, name: "pipeline 4" },
    { id: 5, name: "pipeline 5" },
    { id: 6, name: "pipeline 6" },
    { id: 7, name: "pipeline 7" },
    { id: 8, name: "pipeline 8" },
    { id: 9, name: "pipeline 9" },
  ];

  return (
    <Table>
      <Table.Thead>
        <Table.Tr>
          <Table.Th>Pipeline Id</Table.Th>
          <Table.Th>Pipeline Name</Table.Th>
        </Table.Tr>
      </Table.Thead>
      <Table.Tbody>
        {pipelines.map((pipeline) => (
          <Table.Tr key={pipeline.id}>
            <Table.Td>{pipeline.id}</Table.Td>
            <Table.Td>{pipeline.name}</Table.Td>
          </Table.Tr>
        ))}
      </Table.Tbody>
    </Table>
  );
}
