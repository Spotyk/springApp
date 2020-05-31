<#import "parts/common.ftl" as common>
<#import "/spring.ftl" as spring/>
<@common.page>

    <div class="flex-container">
        <table class="table">
            <tr class="thead-dark ">
                <th scope="col"><@spring.message "userName"/></th>
                <th scope="col"><@spring.message "email"/></th>
                <th scope="col"><@spring.message "country"/></th>
                <th scope="col"><@spring.message "state"/></th>
                <th scope="col"><@spring.message "action"/></th>
            </tr>
            <#list users as user>
                <tr>
                    <form class="form" method="POST" action="/changeUserInfo">
                        <input class="form-control id" type="hidden" name="id" value="${user.id}">
                        <td>
                            <input type="text" class="form-control username" name="username" value="${user.username}"
                                   disabled>
                        </td>
                        <td>
                            <input type="text" class="form-control email" name="email" value="${user.email}" disabled>
                        </td>
                        <td>
                            <input type="text" class="form-control country" name="country" value="${user.country}"
                                   disabled>
                        </td>
                        <td>
                            <input type="text" class="form-control state" name="state" value="${user.state}" disabled>
                        </td>
                        <td>
                            <button class="btn btn-outline-secondary changeable" type="submit"
                                    onclick="makeAbleFields(event)"><@spring.message "change"/></button>

                            <a class="btn btn-outline-secondary changeable"
                                    onclick="deleteUser(${user.id})"> <@spring.message "delete"/></a>
                        </td>
                </tr>
                </form>
            </#list>
        </table>
    </div>
</@common.page>