import { Handle, type HandleProps } from "@xyflow/react";
import style from "./BaseNode.module.css"

const BaseNodePort = ({ type, ...props }: HandleProps) => (
  <Handle
    className={
      type == "source"
        ? `${style["base-node__port"]} ${style["base-node__port_source"]}`
        : style["base-node__port"]
    }
    type={type}
    {...props}
  />
);

export default BaseNodePort;
