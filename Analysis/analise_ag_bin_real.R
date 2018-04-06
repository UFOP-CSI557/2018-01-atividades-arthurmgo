rm(list = rm())

dados  <- read.csv2("data/data-ag-bin-real.txt")
dados$FO <- as.numeric(as.character.numeric_version(dados$FO))


library(plyr)
variavel  <- count(dados,c("Teste"))

show(min(dados$FO))
show(max(dados$FO))
show(sd(dados$F0))


boxplot(FO~Teste, data = dados)