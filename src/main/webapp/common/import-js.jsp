<%@ page contentType="text/html" language="java" %>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.tailwindcss.com"></script>
<script>
    tailwind.config = {
        theme: {
      screens: {
        'sm': {max: "960px"},
      },
    }
  }
</script>
<style>
    #warning-message {
      display: none;
    }
</style>