import { BaseNode } from "@/components/base-node";
import style from "./NodesPanel.module.css"

interface NodesPanelItemProps {
  name: string;
  onDragStart: React.DragEventHandler;
}

const NodesPanelItem = ({ name, onDragStart }: NodesPanelItemProps) => (
  <div
    className={style["nodes-panel__item"]}
    onDragStart={onDragStart}
    draggable>
    <BaseNode>
      <BaseNode.Header>{name}</BaseNode.Header>
    </BaseNode>
  </div>
)

export default NodesPanelItem;
