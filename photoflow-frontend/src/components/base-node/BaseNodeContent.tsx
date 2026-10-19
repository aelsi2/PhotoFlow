import type { PropsWithChildren } from "react";
import style from "./BaseNode.module.css";

const BaseNodeContent = ({ children }: PropsWithChildren) => (
  <div className={style["base-node__content"]}>
    {children}
  </div>
);

export default BaseNodeContent;
