var sys;
var CLR = {
	      branch:"#3498db",
	      feed:"#72c02c",
	      selected:"#C6FFAB"
	    }
function clickNode(title,id){
	var feed=sys.getNode(title+" ["+id+"]");
	sys.eachNode(function(node, pt){
		if(node.data.shape==null){
			node.data.color=CLR.feed;
       		node.data.alpha=0;
		}
		})
	if(feed!=null){		
	    feed.data.alpha=1;
	    feed.data.color=CLR.selected;
	}else{
		console.log("feed sin tag!");
	}
}  
function addNode(feed){
	if(feed.tagsData.length>0){
		var name=feed.title+" ["+feed.id+"]";		
		for(var i=0;i<feed.tagsData.length;i++){
			if(feed.tagsData[i].rootTag){
				sys.addNode(name,{color:CLR.feed, alpha:1,link:'/'+feed.id});
				break;
			}				
		}
		for(var i=0;i<feed.tagsData.length;i++){
			if(feed.tagsData[i].rootTag){
				sys.addEdge(feed.tagsData[i].name,name,{length:10})
			}
		}
	}
}
(function($){  
	var tagsArray=[];
  var Renderer = function(elt){
    var dom = $(elt)
    var canvas = dom.get(0)
    var ctx = canvas.getContext("2d");
    var gfx = arbor.Graphics(canvas)
    var sys = null

    var _vignette = null
    var selected = null,
        nearest = null,
        _mouseP = null;

    
    var that = {
      init:function(pSystem){
        sys = pSystem
        sys.screen({size:{width:dom.width(), height:dom.height()},
                    padding:[50,50,50,50]})

        $(window).resize(that.resize)
        that.resize()
        that._initMouseHandling()

        if (document.referrer.match(/echolalia|atlas|halfviz/)){
          // if we got here by hitting the back button in one of the demos, 
          // start with the demos section pre-selected
          that.switchSection('demos')
        }
      },
      resize:function(){
        canvas.width = .55 * $(window).width()
        canvas.height = .45 * $(window).height()
        sys.screen({size:{width:canvas.width, height:canvas.height}})
        _vignette = null
        that.redraw()
      },
      redraw:function(){
        gfx.clear()
        sys.eachEdge(function(edge, p1, p2){
          if (edge.source.data.alpha * edge.target.data.alpha == 0) return
          gfx.line(p1, p2, {stroke:"#b2b19d", width:2, alpha:edge.target.data.alpha})
        })
        sys.eachNode(function(node, pt){
          var w = Math.max(20, 20+gfx.textWidth(node.name) )
          if (node.data.alpha===0) return
          if (node.data.shape=='dot'){
            gfx.oval(pt.x-w/2, pt.y-w/2, w, w, {fill:node.data.color, alpha:node.data.alpha})
            gfx.text(node.name, pt.x, pt.y+7, {color:"white", align:"center", font:"Arial", size:12})
            gfx.text(node.name, pt.x, pt.y+7, {color:"white", align:"center", font:"Arial", size:12})
          }else{
            gfx.rect(pt.x-w/2, pt.y-8, w, 20, 4, {fill:node.data.color, alpha:node.data.alpha})
            gfx.text(node.name, pt.x, pt.y+9, {color:"white", align:"center", font:"Arial", size:12})
            gfx.text(node.name, pt.x, pt.y+9, {color:"white", align:"center", font:"Arial", size:12})
          }
        })
      },
      switchMode:function(e){
        if (e.mode=='hidden'){
          dom.stop(true).fadeTo(e.dt,0, function(){
            if (sys) sys.stop()
            $(this).hide()
          })
        }else if (e.mode=='visible'){
          dom.stop(true).css('opacity',0).show().fadeTo(e.dt,1,function(){
            that.resize()
          })
          if (sys) sys.start()
        }
      },
      
      switchSection:function(newSection){
        var parent = sys.getEdgesFrom(newSection)[0].source
        var children = $.map(sys.getEdgesFrom(newSection), function(edge){
          return edge.target
        })
        
        sys.eachNode(function(node){
          if (node.data.shape=='dot') return // skip all but leafnodes

          var nowVisible = ($.inArray(node, children)>=0)
          var newAlpha = (nowVisible) ? 1 : 0
          var dt = (nowVisible) ? .5 : .5
          sys.tweenNode(node, dt, {alpha:newAlpha})

          if (newAlpha==1){
            node.p.x = parent.p.x + .05*Math.random() - .025
            node.p.y = parent.p.y + .05*Math.random() - .025
            node.tempMass = .001
          }
        })
      },
      
      
      _initMouseHandling:function(){
        // no-nonsense drag and drop (thanks springy.js)
        selected = null;
        nearest = null;
        var dragged = null;
        var oldmass = 1

        var _section = null

        var handler = {
          moved:function(e){
            var pos = $(canvas).offset();
            _mouseP = arbor.Point(e.pageX-pos.left, e.pageY-pos.top)
            nearest = sys.nearest(_mouseP);

            if (!nearest.node) return false

            if (nearest.node.data.shape!='dot'){
              selected = (nearest.distance < 50) ? nearest : null
              if (selected){
                 dom.addClass('linkable')
                 window.status = selected.node.data.link.replace(/^\//,"http://"+window.location.host+"/").replace(/^#/,'')
              }
              else{
                 dom.removeClass('linkable')
                 window.status = ''
              }
            }else if ($.inArray(nearest.node.name, tagsArray) >=0 ){
              if (nearest.node.name!=_section){
                _section = nearest.node.name
                that.switchSection(_section)
              }
              dom.removeClass('linkable')
              window.status = ''
            }
            
            return false
          },
          clicked:function(e){
            var pos = $(canvas).offset();
            _mouseP = arbor.Point(e.pageX-pos.left, e.pageY-pos.top)
            nearest = dragged = sys.nearest(_mouseP);
            
            if (nearest && selected && nearest.node===selected.node){
            	if(selected.node.data.color!=CLR.selected){            		
            		sys.eachNode(function(node, pt){
                     if(node.data.shape==null)
                            node.data.color=CLR.feed;
            		})
            		selected.node.data.color=CLR.selected;
            	 }else{
            		 sys.eachNode(function(node, pt){
                         if(node.data.shape==null)
                                node.data.color=CLR.feed;
                	});
                	clickMap();
                	return false;
            	 }           	 
              var link = selected.node.data.link
              if (link.match(/^#/)){
                 $(that).trigger({type:"navigate", path:link.substr(1)})
              }else{
            	  var a=angular.element($(".home-container")).scope();
          			a.$apply(function(){a.toggle(link.substr(1));});
              }
              return false
            }
            
            
            if (dragged && dragged.node !== null) dragged.node.fixed = true

            $(canvas).unbind('mousemove', handler.moved);
            $(canvas).bind('mousemove', handler.dragged)
            $(window).bind('mouseup', handler.dropped)

            return false
          },
          dragged:function(e){
            var old_nearest = nearest && nearest.node._id
            var pos = $(canvas).offset();
            var s = arbor.Point(e.pageX-pos.left, e.pageY-pos.top)

            if (!nearest) return
            if (dragged !== null && dragged.node !== null){
              var p = sys.fromScreen(s)
              dragged.node.p = p
            }

            return false
          },

          dropped:function(e){
            if (dragged===null || dragged.node===undefined) return
            if (dragged.node !== null) dragged.node.fixed = false
            dragged.node.tempMass = 1000
            dragged = null;
            // selected = null
            $(canvas).unbind('mousemove', handler.dragged)
            $(window).unbind('mouseup', handler.dropped)
            $(canvas).bind('mousemove', handler.moved);
            _mouseP = null
            return false
          }


        }

        $(canvas).mousedown(handler.clicked);
        $(canvas).mousemove(handler.moved);

      }
    }
    
    return that
  }
  function loadGraph(graph){	  
	  var n={};
	  for(var i=0;i<graph.nodes.length;i++){
		  if(graph.nodes[i].feed==-1)
			  n[graph.nodes[i].name]={color:CLR.branch, shape:"dot", alpha:1};
		  else
			  n[graph.nodes[i].name]={color:CLR.feed, alpha:0,link:'/'+graph.nodes[i].feed};
		  tagsArray.push(graph.nodes[i].name);
	  }
	  var e={};
	  for(var j=0;j<graph.edges.length;j++){
		  var ed=graph.edges[j];
		  var sub={};
		  for(var k=0;k<ed.edges.length;k++){
			  sub[ed.edges[k]]={length:10};
		  }
		  e[ed.name]=sub;
	  }
	  var theUI = {
			  nodes:n,
		      edges:e
		    }

	  	sys = arbor.ParticleSystem({stiffness:5000, repulsion:10000,gravity:false,friction:0.9, dt:0.015})
	  	//sys.parameters()
		sys.renderer = Renderer("#sitemap")
		sys.graft(theUI)
  }
  function errorGraph(message){
	  console.log(message);
  }  
  $("#sitemap").on("reload",function(){
	  $.ajax({
		  dataType: "json",
		  contentType: 'application/json',
		  url: window.location.protocol + "//" + window.location.host+'/feedback/ajax/feed_graph',
		  type: 'GET',
		  data: {feedIds:getFeedIds()},
		  success: loadGraph,
		  error: errorGraph
		});
  })
})(this.jQuery)