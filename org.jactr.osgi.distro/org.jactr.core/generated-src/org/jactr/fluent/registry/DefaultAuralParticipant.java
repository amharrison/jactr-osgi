package org.jactr.fluent.registry;

import java.util.function.Consumer;

import org.jactr.core.chunktype.IChunkType;
import org.jactr.core.model.IModel;
import org.jactr.fluent.FluentChunk;
import org.jactr.fluent.FluentChunkType;

public class DefaultAuralParticipant implements Consumer<IModel>
{

  @Override
  public void accept(IModel model)
  {
    try
    {
      FluentChunkType.from(model).named("audio-event").slots("kind", "location",
          "onset", "offset", "pitch", "azimuth", "elevation").encode();

      IChunkType sound = FluentChunkType.from(model).named("sound")
          .slots("content", "event", "kind").encode();

      FluentChunkType.fromParent(sound).named("tone").slot("pitch").encode();
      FluentChunkType.fromParent(sound).types("digit", "speech", "word");

      IChunkType chunk = model.getDeclarativeModule().getChunkType("chunk")
          .get();

      FluentChunk.from(chunk).chunks("external", "internal");

      FluentChunkType
          .fromParent(
              model.getDeclarativeModule().getChunkType("command").get())
          .named("aural-command")
          .encode();
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

}
