(ns cljgame.utils
  (:import [System Convert]
           [System.IO Path File Directory]
           [System.Reflection Assembly])
  (:require [cljgame.deps :refer [dependencies proj]]))

(defn int32 [n] (Convert/ToInt32 n))

(defn get-prop [obj prop-name] 
    (-> obj .GetType .BaseType (.GetProperty prop-name) (.GetValue obj)))

(def bin-dir (if (= (Environment/GetEnvironmentVariable "CLOJURE_FROM_DOTNET") "True")
                     (-> (Assembly/GetEntryAssembly) .Location Path/GetDirectoryName)
                     (Path/Combine (Directory/GetCurrentDirectory) "bin" (:configuration proj) (:framework proj))))
                     
(defn load-deps! []
  (doseq [dep dependencies] 
    (let [path (Path/Combine bin-dir (str dep ".dll"))]
        (assembly-load-from path))))
