import BaseNodeComponent from "./BaseNode";
import BaseNodeContent from "./BaseNodeContent";
import BaseNodeHeader from "./BaseNodeHeader";

type BaseNodeSubComponents = {
  Header: typeof BaseNodeHeader;
  Content: typeof BaseNodeContent;
}

const BaseNode = BaseNodeComponent as React.FC<React.ComponentProps<typeof BaseNodeComponent>> & BaseNodeSubComponents;

BaseNode.Header = BaseNodeHeader;
BaseNode.Content = BaseNodeContent;

export { BaseNode };
