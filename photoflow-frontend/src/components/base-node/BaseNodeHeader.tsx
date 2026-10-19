import type { PropsWithChildren } from "react";
import style from "./BaseNode.module.css";


const BaseNodeHeader = ({ children }: PropsWithChildren) => (
  <div className={style["base-node__header"]}>
    {children}
  </div>
);

export default BaseNodeHeader;
