using System;
using clojure.clr.api;

Environment.SetEnvironmentVariable("CLOJURE_FROM_DOTNET", bool.TrueString);

var load = Clojure.var("clojure.core", "load");
load.invoke("/cljgame/game");
var run = Clojure.var("cljgame.game", "-main");
run.invoke();