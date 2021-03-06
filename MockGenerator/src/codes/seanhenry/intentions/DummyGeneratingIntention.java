package codes.seanhenry.intentions;

import org.jetbrains.annotations.NotNull;

public class DummyGeneratingIntention extends BaseGeneratingIntention {
  @Override
  protected String getMockType() {
    return "dummy";
  }

  @NotNull
  @Override
  public String getText() {
    return "Generate dummy";
  }
}
