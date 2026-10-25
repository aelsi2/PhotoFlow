import { addEdge, applyEdgeChanges, applyNodeChanges, Background, Controls, Panel, ReactFlow, type ReactFlowInstance } from "@xyflow/react"
import { useCallback, useState } from "react";
import "@xyflow/react/dist/style.css"
import { nodeTypes } from "./nodeTypes";
import { NodesPanel } from "@/components/nodes-panel";
import { NODE_TYPE_DRAG_FORMAT } from "@/components/nodes-panel/constants";

const initialNodes = [];
const initialEdges = [];


const GraphEditor = () => {
  const [nodes, setNodes] = useState(initialNodes);
  const [edges, setEdges] = useState(initialEdges);
  const [flowInstance, setFlowInstance] = useState<ReactFlowInstance<any, any>>(undefined);

  const onNodesChange = useCallback(
    (changes) => setNodes((nodesSnapshot) => applyNodeChanges(changes, nodesSnapshot)),
    [],
  );
  const onEdgesChange = useCallback(
    (changes) => setEdges((edgesSnapshot) => applyEdgeChanges(changes, edgesSnapshot)),
    [],
  );
  const onConnect = useCallback(
    (params) => setEdges((edgesSnapshot) => addEdge(params, edgesSnapshot)),
    [],
  );
  const onDragOver = useCallback((event: React.DragEvent) => {
    event.preventDefault();
    event.dataTransfer.dropEffect = "move";
  }, [])
  const onDrop = useCallback((event: React.DragEvent) => {
    event.preventDefault();

    const nodeType = event.dataTransfer.getData(NODE_TYPE_DRAG_FORMAT);
    if (!nodeType || !flowInstance) return;

    const position = flowInstance.screenToFlowPosition({
      x: event.clientX,
      y: event.clientY
    });

    setNodes((oldNodes) => [
      ...oldNodes, {
        id: `node-${crypto.randomUUID()}`,
        position,
        type: nodeType,
        data: {},
      }
    ]);
  }, [flowInstance]);

  return (
    <div style={{ width: "100%", height: "100vh" }}>
      <ReactFlow
        onInit={(instance) => setFlowInstance(instance)}
        nodes={nodes}
        edges={edges}
        onNodesChange={onNodesChange}
        onEdgesChange={onEdgesChange}
        onConnect={onConnect}
        onDrop={onDrop}
        onDragOver={onDragOver}
        nodeTypes={nodeTypes}
        fitView
      >
        <Controls />
        <Background />
        <Panel position="bottom-center">
          <NodesPanel nodeNames={Object.keys(nodeTypes)} />
        </Panel>
      </ReactFlow>
    </div>
  )
}

export default GraphEditor
