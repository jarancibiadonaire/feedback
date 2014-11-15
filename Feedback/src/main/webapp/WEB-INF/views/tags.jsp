<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="modal fade" id="responsive" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Configuración de tags</h4>
			</div>
			<form:form action='${pageContext.request.contextPath}/${param.url}'
				class="sky-form without-border" id="config-tag-form"
				modelAttribute="configTag">
				<div class="modal-body">
					<div class="row">
						<form:input path="username"
							value="${pageContext.request.userPrincipal.name}" class="hidden" />
						<h5>Selecciona los tags que deseas seguir:</h5>
						<ul id="pageStuff" class="ul-tags">
							<c:forEach items="${listTags}" var="tag">
								<li><div class="margin-bottom-5 inline">
										<form:checkbox name="tag-config" value="${tag.id}"
											path="tagsIds" />
										<button
											class="btn-u rounded-2x btn-u-sm ${tag.rootTag ? 'btn-u-blue' : 'btn-u-default'}"
											type="button">
											<i class="fa fa-tag"></i>${tag.name}
										</button>
										<c:if
											test="${tag.user == pageContext.request.userPrincipal.name}">
											<i class="fa fa-user"></i>
										</c:if>
									</div></li>
							</c:forEach>
						</ul>
						<div class="span6 text-center">
							<div class="pagination">
								<ul class="pager"></ul>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn-u btn-u-default"
						data-dismiss="modal">Cerrar</button>
					<button type="submit" class="btn-u btn-u-primary">Guardar
						cambios</button>
				</div>
			</form:form>
		</div>
	</div>
</div>