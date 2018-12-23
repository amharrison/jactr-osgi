package org.jactr.tools.experiment.parser.handlers;

/*
 * default logging
 */
import org.jactr.tools.experiment.IExperiment;
import org.jactr.tools.experiment.actions.IAction;
import org.jactr.tools.experiment.actions.common.EndExperimentAction;
import org.w3c.dom.Element;

public class EndExperimentHandler implements INodeHandler<IAction>
{
  public String getTagName()
  {
    return "end-experiment";
  }

  public IAction process(Element element, IExperiment experiment)
  {
    return new EndExperimentAction(experiment);
  }

  public boolean shouldDecend()
  {
    return false;
  }
}