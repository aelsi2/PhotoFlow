import { useState } from "react"
import { BaseNode } from "@/components/base-node";
import { Position } from "@xyflow/react";

const TextInputNode = () => {
  const [text, setText] = useState("");

  return (
    <BaseNode>
      <BaseNode.Header>Text Input</BaseNode.Header>
      <BaseNode.Port type="source" position={Position.Bottom} />
      <BaseNode.Port type="target" position={Position.Top} />
      <BaseNode.Content>
        <label htmlFor="text">Text: </label>
        <input
          id="text"
          value={text}
          onChange={(event) => setText(event.target.value)}
          className="nodrag"
        />
      </BaseNode.Content>
    </BaseNode>
  )
}

export default TextInputNode;
