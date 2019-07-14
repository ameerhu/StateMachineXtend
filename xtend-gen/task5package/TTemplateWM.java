package task5package;

import com.google.common.base.Objects;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import task5package.Action;
import task5package.ComplexState;
import task5package.State;
import task5package.StateMachine;
import task5package.Transition;

@SuppressWarnings("all")
public class TTemplateWM {
  private String pkgName = "generatedFilesWM";
  
  private String path = (("src/" + this.pkgName) + "/");
  
  private BufferedWriter writer;
  
  private String data;
  
  public String writeFiles(final StateMachine main) {
    String _xblockexpression = null;
    {
      CharSequence _genInterface = this.genInterface(main.getActions());
      String _plus = (_genInterface + "");
      this.fileWrite("State", _plus);
      State[] _states = main.getStates();
      for (final State s : _states) {
        this.genStateClass(s, main.getActions());
      }
      CharSequence _genContext = this.genContext(main.getStates()[0], main.getActions());
      String _plus_1 = (_genContext + "");
      this.fileWrite("ContextMachine", _plus_1);
      CharSequence _genTestFile = this.genTestFile();
      String _plus_2 = (_genTestFile + "");
      _xblockexpression = this.fileWrite("TestStateMachine", _plus_2);
    }
    return _xblockexpression;
  }
  
  public String fileWrite(final String name, final String data) {
    try {
      String _xblockexpression = null;
      {
        FileWriter _fileWriter = new FileWriter(((this.path + name) + ".java"));
        BufferedWriter _bufferedWriter = new BufferedWriter(_fileWriter);
        this.writer = _bufferedWriter;
        this.writer.write(data);
        this.writer.close();
        _xblockexpression = InputOutput.<String>println((name + " written."));
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public CharSequence genInterface(final Action[] actions) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.pkgName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("interface State{");
    _builder.newLine();
    {
      for(final Action a : actions) {
        _builder.append("\t");
        _builder.append("public void ");
        String _name = a.getName();
        _builder.append(_name, "\t");
        _builder.append("(ContextMachine context);");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public Object resolveState(final State s) {
    Object _xifexpression = null;
    boolean _isComplex = s.isComplex();
    if (_isComplex) {
      _xifexpression = this.resolveState(((ComplexState) s).getStateMachine().getStates()[0]);
    } else {
      _xifexpression = s.getName();
    }
    return _xifexpression;
  }
  
  public void genStateClass(final State s, final Action[] actions) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.pkgName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      if ((Objects.equal(s.getZuper(), null) && (!s.isComplex()))) {
        _builder.append("public class ");
        String _name = s.getName();
        _builder.append(_name);
        _builder.append(" implements State{");
        _builder.newLineIfNotEmpty();
        {
          Transition[] _outgoings = s.getOutgoings();
          boolean _tripleNotEquals = (_outgoings != null);
          if (_tripleNotEquals) {
            {
              Transition[] _outgoings_1 = s.getOutgoings();
              for(final Transition st : _outgoings_1) {
                {
                  Action _action = st.getAction();
                  boolean _tripleNotEquals_1 = (_action != null);
                  if (_tripleNotEquals_1) {
                    _builder.append("\t");
                    _builder.append("public void ");
                    String _name_1 = st.getAction().getName();
                    _builder.append(_name_1, "\t");
                    _builder.append(" (ContextMachine wrapper){");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("wrapper.setState(new ");
                    Object _resolveState = this.resolveState(st.getTarget());
                    _builder.append(_resolveState, "\t\t");
                    _builder.append("());");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("System.out.println(\"");
                    Object _resolveState_1 = this.resolveState(st.getTarget());
                    _builder.append(_resolveState_1, "\t\t");
                    _builder.append("\");");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("}");
                    _builder.newLine();
                  }
                }
              }
            }
          }
        }
        {
          if ((actions != null)) {
            {
              for(final Action a : actions) {
                {
                  Transition[] _outgoings_2 = s.getOutgoings();
                  boolean _tripleNotEquals_2 = (_outgoings_2 != null);
                  if (_tripleNotEquals_2) {
                    {
                      Transition[] _outgoings_3 = s.getOutgoings();
                      for(final Transition o : _outgoings_3) {
                        {
                          Action _action_1 = o.getAction();
                          boolean _tripleNotEquals_3 = (_action_1 != null);
                          if (_tripleNotEquals_3) {
                            {
                              String _name_2 = o.getAction().getName();
                              String _name_3 = a.getName();
                              boolean _tripleNotEquals_4 = (_name_2 != _name_3);
                              if (_tripleNotEquals_4) {
                                _builder.append("\t");
                                _builder.append("public void ");
                                String _name_4 = a.getName();
                                _builder.append(_name_4, "\t");
                                _builder.append("(ContextMachine wrapper){");
                                _builder.newLineIfNotEmpty();
                                _builder.append("\t");
                                _builder.append("\t");
                                _builder.append("System.out.println(\"Can\'t Performed this action\");");
                                _builder.newLine();
                                _builder.append("\t");
                                _builder.append("}");
                                _builder.newLine();
                              }
                            }
                          }
                        }
                      }
                    }
                  } else {
                    _builder.append("\t");
                    _builder.append("public void ");
                    String _name_5 = a.getName();
                    _builder.append(_name_5, "\t");
                    _builder.append("(ContextMachine wrapper){");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("System.out.println(\"Can\'t Performed this action\");");
                    _builder.newLine();
                    _builder.append("\t");
                    _builder.append("}");
                    _builder.newLine();
                  }
                }
              }
            }
          }
        }
        _builder.append("} ");
        _builder.newLine();
      } else {
        if ((s.isComplex() && Objects.equal(s.getZuper(), null))) {
          _builder.append("public abstract class ");
          String _name_6 = s.getName();
          _builder.append(_name_6);
          _builder.append(" implements State{");
          _builder.newLineIfNotEmpty();
          {
            Transition[] _outgoings_4 = s.getOutgoings();
            boolean _tripleNotEquals_5 = (_outgoings_4 != null);
            if (_tripleNotEquals_5) {
              {
                Transition[] _outgoings_5 = s.getOutgoings();
                for(final Transition st_1 : _outgoings_5) {
                  _builder.append("\t");
                  _builder.append("public void ");
                  String _name_7 = st_1.getAction().getName();
                  _builder.append(_name_7, "\t");
                  _builder.append(" (ContextMachine wrapper){");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("wrapper.setState(new ");
                  String _name_8 = st_1.getTarget().getName();
                  _builder.append(_name_8, "\t\t");
                  _builder.append("());");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("System.out.println(\"");
                  String _name_9 = st_1.getTarget().getName();
                  _builder.append(_name_9, "\t\t");
                  _builder.append("\");");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("}");
                  _builder.newLine();
                }
              }
            }
          }
          {
            if ((actions != null)) {
              {
                for(final Action a_1 : actions) {
                  {
                    Transition[] _outgoings_6 = s.getOutgoings();
                    boolean _tripleNotEquals_6 = (_outgoings_6 != null);
                    if (_tripleNotEquals_6) {
                      {
                        Transition[] _outgoings_7 = s.getOutgoings();
                        for(final Transition o_1 : _outgoings_7) {
                          {
                            String _name_10 = o_1.getAction().getName();
                            String _name_11 = a_1.getName();
                            boolean _tripleNotEquals_7 = (_name_10 != _name_11);
                            if (_tripleNotEquals_7) {
                              _builder.append("\t");
                              _builder.append("public abstract void ");
                              String _name_12 = a_1.getName();
                              _builder.append(_name_12, "\t");
                              _builder.append("(ContextMachine wrapper);");
                              _builder.newLineIfNotEmpty();
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
          _builder.append("}");
          _builder.newLine();
        } else {
          if ((s.isComplex() && (s.getZuper() != null))) {
            _builder.append("public abstract class ");
            String _name_13 = s.getName();
            _builder.append(_name_13);
            _builder.append(" extends ");
            String _name_14 = s.getZuper().getName();
            _builder.append(_name_14);
            _builder.append("{");
            _builder.newLineIfNotEmpty();
            {
              Transition[] _outgoings_8 = s.getOutgoings();
              boolean _tripleNotEquals_8 = (_outgoings_8 != null);
              if (_tripleNotEquals_8) {
                {
                  Transition[] _outgoings_9 = s.getOutgoings();
                  for(final Transition st_2 : _outgoings_9) {
                    _builder.append("\t");
                    _builder.append("public void ");
                    String _name_15 = st_2.getAction().getName();
                    _builder.append(_name_15, "\t");
                    _builder.append(" (ContextMachine wrapper){");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("wrapper.setState(new ");
                    String _name_16 = st_2.getTarget().getName();
                    _builder.append(_name_16, "\t\t");
                    _builder.append("());");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("System.out.println(\"");
                    String _name_17 = st_2.getTarget().getName();
                    _builder.append(_name_17, "\t\t");
                    _builder.append("\");");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("}");
                    _builder.newLine();
                  }
                }
              }
            }
            {
              if ((actions != null)) {
                {
                  for(final Action a_2 : actions) {
                    {
                      Transition[] _outgoings_10 = s.getOutgoings();
                      boolean _tripleNotEquals_9 = (_outgoings_10 != null);
                      if (_tripleNotEquals_9) {
                        {
                          Transition[] _outgoings_11 = s.getOutgoings();
                          for(final Transition o_2 : _outgoings_11) {
                            {
                              String _name_18 = o_2.getAction().getName();
                              String _name_19 = a_2.getName();
                              boolean _tripleNotEquals_10 = (_name_18 != _name_19);
                              if (_tripleNotEquals_10) {
                                _builder.append("\t");
                                _builder.append("public abstract void ");
                                String _name_20 = a_2.getName();
                                _builder.append(_name_20, "\t");
                                _builder.append("(ContextMachine wrapper);");
                                _builder.newLineIfNotEmpty();
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            _builder.append("}");
            _builder.newLine();
          } else {
            _builder.append("public class ");
            String _name_21 = s.getName();
            _builder.append(_name_21);
            _builder.append(" extends ");
            String _name_22 = s.getZuper().getName();
            _builder.append(_name_22);
            _builder.append("{");
            _builder.newLineIfNotEmpty();
            {
              Transition[] _outgoings_12 = s.getOutgoings();
              boolean _tripleNotEquals_11 = (_outgoings_12 != null);
              if (_tripleNotEquals_11) {
                {
                  Transition[] _outgoings_13 = s.getOutgoings();
                  for(final Transition st_3 : _outgoings_13) {
                    _builder.append("\t");
                    _builder.append("public void ");
                    String _name_23 = st_3.getAction().getName();
                    _builder.append(_name_23, "\t");
                    _builder.append(" (ContextMachine wrapper){");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("wrapper.setState(new ");
                    Object _resolveState_2 = this.resolveState(st_3.getTarget());
                    _builder.append(_resolveState_2, "\t\t");
                    _builder.append("());");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t");
                    _builder.append("System.out.println(\"");
                    Object _resolveState_3 = this.resolveState(st_3.getTarget());
                    _builder.append(_resolveState_3, "\t\t");
                    _builder.append("\");");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("}");
                    _builder.newLine();
                  }
                }
              }
            }
            {
              if ((actions != null)) {
                {
                  for(final Action a_3 : actions) {
                    {
                      Transition[] _outgoings_14 = s.getOutgoings();
                      boolean _tripleNotEquals_12 = (_outgoings_14 != null);
                      if (_tripleNotEquals_12) {
                        {
                          Transition[] _outgoings_15 = s.getOutgoings();
                          for(final Transition o_3 : _outgoings_15) {
                            {
                              String _name_24 = o_3.getAction().getName();
                              String _name_25 = a_3.getName();
                              boolean _tripleNotEquals_13 = (_name_24 != _name_25);
                              if (_tripleNotEquals_13) {
                                _builder.append("\t");
                                _builder.append("public void ");
                                String _name_26 = a_3.getName();
                                _builder.append(_name_26, "\t");
                                _builder.append("(ContextMachine wrapper){");
                                _builder.newLineIfNotEmpty();
                                _builder.append("\t");
                                _builder.append("\t");
                                _builder.append("System.out.println(\"Can\'t Performed this action\");");
                                _builder.newLine();
                                _builder.append("\t");
                                _builder.append("}");
                                _builder.newLine();
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    this.data = _builder.toString();
    this.fileWrite(s.getName(), this.data);
    boolean _isComplex = s.isComplex();
    if (_isComplex) {
      State[] _states = ((ComplexState) s).getStateMachine().getStates();
      for (final State st_4 : _states) {
        this.genStateClass(st_4, ((ComplexState) s).getStateMachine().getActions());
      }
    }
  }
  
  public CharSequence genContext(final State state, final Action[] actions) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.pkgName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("class ContextMachine {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("private State currentState;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public ContextMachine() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("currentState = new ");
    String _name = state.getName();
    _builder.append(_name, "        ");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("System.out.println(\"");
    String _name_1 = state.getName();
    _builder.append(_name_1, "        ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public void setState(State s) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("currentState = s;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    {
      for(final Action a : actions) {
        _builder.append("    ");
        _builder.append("public void ");
        String _name_2 = a.getName();
        _builder.append(_name_2, "    ");
        _builder.append("(){");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("\t");
        _builder.append("currentState.");
        String _name_3 = a.getName();
        _builder.append(_name_3, "    \t");
        _builder.append("(this);");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence genTestFile() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    _builder.append(this.pkgName);
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public class TestStateMachine{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static void main(String args[]){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ContextMachine context = new ContextMachine();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
