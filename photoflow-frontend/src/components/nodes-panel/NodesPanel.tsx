import { NODE_TYPE_DRAG_FORMAT } from "./constants";
import style from "./NodesPanel.module.css";
import NodesPanelItem from "./NodesPanelItem";

interface NodesPanelProps {
  nodeNames: string[],
}

const handleNodeDragStart = (event: React.DragEvent, nodeName: string) => {
  event.dataTransfer.setData(NODE_TYPE_DRAG_FORMAT, nodeName);
  event.dataTransfer.effectAllowed = "move";
}

const NodesPanel = ({ nodeNames }: NodesPanelProps) => (
  <div className={style["nodes-panel"]}>
    {nodeNames.map((name) => <NodesPanelItem
      key={name}
      name={name}
      onDragStart={(event) => handleNodeDragStart(event, name)}
    />
    )}
  </div>
);

export default NodesPanel;
