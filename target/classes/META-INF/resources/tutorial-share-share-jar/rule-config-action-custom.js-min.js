if(typeof Civ=="undefined"||!Civ){var Civ={}}(function(){var d=YAHOO.util.Dom,c=YAHOO.util.Selector,b=YAHOO.util.Event;var a=Alfresco.util.encodeHTML,e=Alfresco.util.hasEventInterest;Civ.RuleConfigActionCustom=function(f){Civ.RuleConfigActionCustom.superclass.constructor.call(this,f);this.name="SomeCo.RuleConfigActionCustom";Alfresco.util.ComponentManager.reregister(this);this.customisations=YAHOO.lang.merge(this.customisations,SomeCo.RuleConfigActionCustom.superclass.customisations);this.renderers=YAHOO.lang.merge(this.renderers,SomeCo.RuleConfigActionCustom.superclass.renderers);return this};YAHOO.extend(Civ.RuleConfigActionCustom,Alfresco.RuleConfigAction,{customisations:{CreateFolderCiv:{text:function(h,g,f){this._getParamDef(h,"destination-folder")._type="path";return h},edit:function(h,g,f){this._hideParameters(h.parameterDefinitions);h.parameterDefinitions.splice(0,0,{type:"arca:destination-dialog-button",displayLabel:this.msg("label.to"),_buttonLabel:this.msg("button.select-folder"),_destinationParam:"destination-folder"});return h}},},})})();