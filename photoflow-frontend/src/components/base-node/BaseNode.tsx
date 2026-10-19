import type { PropsWithChildren } from "react";
import style from "./BaseNode.module.css";

const BaseNode = ({ children }: PropsWithChildren) => (
  <div className={style["base-node"]}>
    {children}
  </div>
);

export default BaseNode;
