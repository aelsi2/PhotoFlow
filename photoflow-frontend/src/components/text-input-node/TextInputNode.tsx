import { useState } from "react"
import { BaseNode } from "@/components/base-node";

const TextInputNode = () => {
  const [text, setText] = useState("");

  return (
    <BaseNode>
      <BaseNode.Header>Text Input</BaseNode.Header>
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
