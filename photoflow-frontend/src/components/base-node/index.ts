import BaseNodeComponent from "./BaseNode";
import BaseNodeContent from "./BaseNodeContent";
import BaseNodeHeader from "./BaseNodeHeader";
import BaseNodePort from "./BaseNodePort";

type BaseNodeSubComponents = {
  Header: typeof BaseNodeHeader;
  Content: typeof BaseNodeContent;
  Port: typeof BaseNodePort;
}

const BaseNode = BaseNodeComponent as React.FC<React.ComponentProps<typeof BaseNodeComponent>> & BaseNodeSubComponents;

BaseNode.Header = BaseNodeHeader;
BaseNode.Content = BaseNodeContent;
BaseNode.Port = BaseNodePort;

export { BaseNode };
