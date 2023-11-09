<template>

    <v-data-table
        :headers="headers"
        :items="menuSelect"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'MenuSelectView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            menuSelect : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/menuSelects'))

            temp.data._embedded.menuSelects.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.menuSelect = temp.data._embedded.menuSelects;
        },
        methods: {
        }
    }
</script>

