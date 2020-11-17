package shared.util;

import java.beans.PropertyChangeListener;

public interface Subject
{
  void addListener(PropertyChangeListener listener);
  void addListener(String name, PropertyChangeListener listener);
  void removeListener(PropertyChangeListener listener);
  void removeListener(String name, PropertyChangeListener listener);
}
